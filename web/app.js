// INTRO ANIMATION CONTROL
(function() {
  const prefersReducedMotion = window.matchMedia('(prefers-reduced-motion: reduce)').matches;
  const introDuration = prefersReducedMotion ? 800 : 4500;
  const overlay = document.getElementById('introOverlay');
  
  document.body.classList.add('intro-active');
  
  setTimeout(() => {
    document.body.classList.remove('intro-active');
    
    setTimeout(() => {
      overlay.style.display = 'none';
    }, 600);
  }, introDuration);
})();

// Página de lujo - Sin loops ni recargas
let cart = [];
let vehiclesData = [];

// Cargar vehículos
fetch('http://localhost:4567/api/vehicles')
  .then(r => r.json())
  .then(vehicles => {
    vehiclesData = vehicles;
    const grid = document.querySelector('.vehicles-grid');
    const images = [
      'https://commons.wikimedia.org/wiki/Special:FilePath/Ferrari_F8_Tributo%2C_GIMS_2019%2C_Le_Grand-Saconnex_%28GIMS1318%29.jpg?width=1200',
      'https://commons.wikimedia.org/wiki/Special:FilePath/2022_Lamborghini_Huracan_EVO_Orange.jpg?width=1200',
      'https://commons.wikimedia.org/wiki/Special:FilePath/Porsche_992_Turbo_S_1X7A0411.jpg?width=1200',
      'https://commons.wikimedia.org/wiki/Special:FilePath/McLaren_720S_Spider_Genf_2019_1Y7A5614.jpg?width=1200',
      'https://commons.wikimedia.org/wiki/Special:FilePath/2021_Mercedes-AMG_GT_Black_Series.jpg?width=1200',
      'https://commons.wikimedia.org/wiki/Special:FilePath/Aston_Martin_DB12_1X7A1928.jpg?width=1200',
      'https://commons.wikimedia.org/wiki/Special:FilePath/2019_Bentley_Continental_GT_V8_Automatic_4.0_Front.jpg?width=1200',
      'https://commons.wikimedia.org/wiki/Special:FilePath/Rolls-Royce_Phantom_VIII_001.jpg?width=1200',
      'https://commons.wikimedia.org/wiki/Special:FilePath/2020_Bugatti_Chiron_Sport_in_Nocturne_and_Atlantic_Blue%2C_front_left.jpg?width=1200',
      'https://commons.wikimedia.org/wiki/Special:FilePath/2023_Maserati_MC20_in_Digital_Mint%2C_rear_right.jpg?width=1200'
    ];
    
    grid.innerHTML = vehicles.map((v, i) => `
      <div class="vehicle-card">
        <div class="vehicle-image" onclick="showVehicleModal(${v.id})" style="cursor: pointer;">
          <img src="${images[i]}" alt="${v.marca} ${v.modelo}">
        </div>
        <div class="vehicle-content">
          <h3 class="vehicle-name">${v.marca} ${v.modelo}</h3>
          <p class="vehicle-year">Year ${v.año}</p>
          <p class="vehicle-desc">${v.extras}</p>
          <div class="vehicle-footer">
            <span class="vehicle-price">$${v.precio.toLocaleString()}</span>
            <button class="btn-add-cart" onclick="addToCart(${v.id}, '${v.marca} ${v.modelo}', ${v.precio})">Add to Cart</button>
          </div>
        </div>
      </div>
    `).join('');
  })
  .catch(() => {
    document.querySelector('.vehicles-grid').innerHTML = '<p style="grid-column:1/-1;text-align:center;padding:4rem;color:#d4af37">Error loading vehicles</p>';
  });

// Funciones del carrito
function addToCart(id, name, price) {
  if (cart.find(i => i.id === id)) { alert('Already in cart'); return; }
  cart.push({ id, name, price });
  updateCart();
  document.getElementById('cartPanel').classList.add('active');
}

function removeFromCart(id) {
  cart = cart.filter(i => i.id !== id);
  updateCart();
}

function updateCart() {
  document.getElementById('cartCount').textContent = cart.length;
  const items = document.getElementById('cartItems');
  const total = document.getElementById('cartTotal');
  
  if (cart.length === 0) {
    items.innerHTML = '<p style="text-align:center;padding:2rem;opacity:0.6">Empty cart</p>';
    total.textContent = '$0';
  } else {
    items.innerHTML = cart.map(i => `
      <div class="cart-item">
        <div><h4>${i.name}</h4><p>$${i.price.toLocaleString()}</p></div>
        <button class="btn-remove" onclick="removeFromCart(${i.id})">Remove</button>
      </div>
    `).join('');
    total.textContent = '$' + cart.reduce((s, i) => s + i.price, 0).toLocaleString();
  }
}

function checkout() {
  if (!cart.length) { alert('Cart is empty'); return; }
  const nombre = prompt('Your name:');
  if (!nombre) return;
  
  fetch('http://localhost:4567/api/checkout', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ cliente: nombre, items: cart.map(i => i.id) })
  })
  .then(r => r.json())
  .then(t => {
    showReceiptModal(t);
    cart = [];
    updateCart();
    document.getElementById('cartPanel').classList.remove('active');
  });
}

function escapeHtml(s) {
  return String(s)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&#039;');
}

function showReceiptModal(ticket) {
  const items = Array.isArray(ticket.items) ? ticket.items : [];
  const itemsHtml = items.length
    ? items.map(v => {
        const name = `${v.marca} ${v.modelo}`;
        const year = v.año;
        const km = (v.kilometros ?? 0);
        const price = (v.precio ?? 0);
        return `
          <div class="info-item full-width">
            <span class="info-label">${escapeHtml(name)} (${escapeHtml(year)})</span>
            <span class="info-value">$${Number(price).toLocaleString()} • ${Number(km).toLocaleString()} km</span>
          </div>
        `;
      }).join('')
    : `
      <div class="info-item full-width">
        <span class="info-label">Items</span>
        <span class="info-value">(sin items)</span>
      </div>
    `;

  const txtUrl = ticket.downloadTxt ? `http://localhost:4567${ticket.downloadTxt}` : null;
  const jsonUrl = ticket.downloadJson ? `http://localhost:4567${ticket.downloadJson}` : null;

  const modal = document.createElement('div');
  modal.className = 'vehicle-modal';
  modal.innerHTML = `
    <div class="modal-overlay" onclick="this.parentElement.remove()"></div>
    <div class="modal-content">
      <button class="modal-close" onclick="this.closest('.vehicle-modal').remove()">×</button>
      <h2>Ticket de compra</h2>
      <div class="modal-info">
        <div class="info-item">
          <span class="info-label">Ticket ID</span>
          <span class="info-value">${escapeHtml(ticket.id ?? '—')}</span>
        </div>
        <div class="info-item">
          <span class="info-label">Cliente</span>
          <span class="info-value">${escapeHtml(ticket.cliente ?? '—')}</span>
        </div>
        <div class="info-item">
          <span class="info-label">Fecha</span>
          <span class="info-value">${escapeHtml(ticket.fecha ?? '—')}</span>
        </div>
        <div class="info-item">
          <span class="info-label">Total</span>
          <span class="info-value">$${Number(ticket.total ?? 0).toLocaleString()}</span>
        </div>
        ${itemsHtml}
      </div>

      <div style="display:flex; gap:12px; flex-wrap:wrap; margin-top:16px;">
        ${txtUrl ? `<a class="btn-add-cart" href="${txtUrl}" target="_blank" rel="noopener">Descargar Ticket (TXT)</a>` : ''}
        ${jsonUrl ? `<a class="btn-add-cart" href="${jsonUrl}" target="_blank" rel="noopener">Ver Ticket (JSON)</a>` : ''}
        <button class="btn-add-cart" onclick="this.closest('.vehicle-modal').remove()">Cerrar</button>
      </div>
    </div>
  `;

  document.body.appendChild(modal);
}

// Mostrar modal con info del vehículo
function showVehicleModal(id) {
  const vehicle = vehiclesData.find(v => v.id === id);
  if (!vehicle) return;
  
  const modal = document.createElement('div');
  modal.className = 'vehicle-modal';
  modal.innerHTML = `
    <div class="modal-overlay" onclick="this.parentElement.remove()"></div>
    <div class="modal-content">
      <button class="modal-close" onclick="this.closest('.vehicle-modal').remove()">×</button>
      <h2>${vehicle.marca} ${vehicle.modelo}</h2>
      <div class="modal-info">
        <div class="info-item">
          <span class="info-label">Año</span>
          <span class="info-value">${vehicle.año}</span>
        </div>
        <div class="info-item">
          <span class="info-label">Precio</span>
          <span class="info-value">$${vehicle.precio.toLocaleString()}</span>
        </div>
        <div class="info-item">
          <span class="info-label">Kilometraje</span>
          <span class="info-value">${vehicle.kilometros.toLocaleString()} km</span>
        </div>
        <div class="info-item full-width">
          <span class="info-label">Características</span>
          <span class="info-value">${vehicle.extras}</span>
        </div>
      </div>
      <button class="btn-add-cart" onclick="addToCart(${vehicle.id}, '${vehicle.marca} ${vehicle.modelo}', ${vehicle.precio}); this.closest('.vehicle-modal').remove();">
        Add to Cart
      </button>
    </div>
  `;
  document.body.appendChild(modal);
}

// Setup UI
document.getElementById('cartToggle').onclick = () => document.getElementById('cartPanel').classList.add('active');
document.getElementById('cartClose').onclick = () => document.getElementById('cartPanel').classList.remove('active');
document.getElementById('checkoutBtn').onclick = checkout;

window.addEventListener('scroll', () => {
  if (window.pageYOffset > 80) document.getElementById('header').classList.add('scrolled');
  else document.getElementById('header').classList.remove('scrolled');
}, { passive: true });


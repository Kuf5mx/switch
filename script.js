const switchBtn = document.getElementById('switchBtn');
const body = document.body;
let isOn = false;

switchBtn.addEventListener('click', function() {
    isOn = !isOn;
    
    if (isOn) {
        body.classList.add('on');
        switchBtn.classList.add('on');
    } else {
        body.classList.remove('on');
        switchBtn.classList.remove('on');
    }
});
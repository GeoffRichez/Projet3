const text = document.querySelector('.CountDown');
const date1 = new Date(document.getElementById("date").getAttribute("data-date"));
function countDown() {
	const result = "Cet événement est terminé";
    const now = new Date().getTime();
    const countdownDate = new Date(date1).getTime();
    const distanceBase = countdownDate - now;
    const days = Math.floor(distanceBase / (1000 * 60 * 60 * 24));
    const hours = Math.floor((distanceBase % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minutes = Math.floor((distanceBase % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((distanceBase % (1000 * 60)) / 1000);
  	
    if (date1 < now) {
    	return text.innerText = result;
  	} else {
    	return text.innerText = `${days}j ${hours}h ${minutes}m ${seconds}s`;
  };
};
countDown();
const countDownInterval = setInterval(() => {
    countDown();
}, 1000);
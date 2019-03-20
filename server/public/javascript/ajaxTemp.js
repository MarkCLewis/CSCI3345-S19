/**
 * Do Ajax stuff!
 */

const field = document.getElementById("av-year")
const text = document.getElementById("text-for-av-temp")

field.onkeydown = (event) => {
	if(event.key ==='Enter') {
		const year = field.value;
		fetch("/averageYearlyHigh/"+year).then(response => {
			return response.text();
		}).then(res => {
			text.innerHTML = res
		});
	}
}
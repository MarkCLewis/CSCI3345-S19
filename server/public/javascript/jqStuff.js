$(init)

function init() {
	console.log("In init.");
	$("td").css("color", "blue");
	$("td").click(function (e) {
		$(this).css("color", "red");
	});
}
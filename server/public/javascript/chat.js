/**
 * A little code for doing a chat app.
 */

const input = document.getElementById("input-field");
const output = document.getElementById("chat-text");

const wsRoute = document.getElementById("ws-route");
const socket = new WebSocket(wsRoute.value.replace("http", "ws"));

input.onkeydown = (event) => {
	if(event.key ==='Enter') {
		socket.send(input.value);
		input.value = '';
	}
}

socket.addEventListener('open', function (event) {
    socket.send('A new user has arrived.');
});

socket.addEventListener('message', function (event) {
    output.value += '\n' + event.data;
});

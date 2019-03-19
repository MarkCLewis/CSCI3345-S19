/**
 * A little code for doing a chat app.
 */

const input = document.getElementById("input-field");
const output = document.getElementById("chat-text");

const socket = new WebSocket("ws://localhost:9000/chatSocket");

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
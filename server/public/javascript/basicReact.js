/**
 * Some basic react stuff to demonstrate. 
 */

ReactDOM.render(
  React.createElement('h1', null, 'Hello world!'),
  document.getElementById('content')
)

/* Here is a component based approach that creates two Hello Worlds.

let h1 = React.createElement('h1', null, 'Hello world!')
class HelloWorld extends React.Component {
	render() {
		return React.createElement('div', null, h1, h1)
	}
}
ReactDOM.render(
	React.createElement(HelloWorld, null),
	document.getElementById('content')
)
*/
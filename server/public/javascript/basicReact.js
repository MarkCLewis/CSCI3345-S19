/**
 * Some basic react stuff to demonstrate. 
 */
/*
ReactDOM.render(
  React.createElement('h1', null, 'Hello world!'),
  document.getElementById('content')
)

 Here is a component based approach that creates two Hello Worlds.
*/

let H1 = (props) => React.createElement('h1', props, 'Hello world!')
class HelloWorld extends React.Component {
	constructor(p) {
		super(p);
		state = {i: 5};
	}
	foo() {
		setState({i: 42});
	}
	render() {
		return React.createElement('div', null, H1({style = 'color: "red"'}), H1);
	}
}
ReactDOM.render(
	React.createElement(HelloWorld, null),
	document.getElementById('content')
)
*/
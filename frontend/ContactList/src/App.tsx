import React from 'react';
import './App.css';
import Contacts from './pages/contacts/Contacts';

interface AppProps {}

function App({}: AppProps) {

	return (
		<div className="App">
			<div className="app-container">
				<div className="spacing-top"/>
				<Contacts />
			</div>
		</div>
	);
}

export default App;

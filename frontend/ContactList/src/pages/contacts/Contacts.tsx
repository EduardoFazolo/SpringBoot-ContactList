import React, { useEffect, useState } from 'react';
import type { ContactDto } from 'src/models/ContactDto';
import ContactList from './contactList/ContactList';
import './Contacts.css';
import { ContactService } from './ContactService';

interface Contacts {}

function Contacts({}: Contacts) {
	const [contacts, setContacts] = useState([] as ContactDto[]);

	async function getContacts() {
		const apiContacts = await ContactService.getContacts({pageIndex:0, pageSize: 10, searchString:""});
		setContacts(apiContacts);
	}

	useEffect(()=>{
		getContacts();
	}, []);

	return (
		<div className="Contacts">
			<ContactList items={contacts}/>
		</div>
	);
}

export default Contacts;

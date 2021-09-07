import React, { useCallback } from 'react';
import type { ContactDto } from 'src/models/ContactDto';
import { ContactService } from '../ContactService';
import './ContactItem.css';

interface ContactItem {
	item: ContactDto;
}

function ContactItem({item}: ContactItem) {

	const removeContact = useCallback( async () => {
		await ContactService.deleteContacts(item.id!);
	}, []);

	return (
		<li className="ContactItem" key={item.name}>
			<div className="delete noselect" onClick={removeContact}>&#x2715;</div>
			<div className="name">
				{item.name} {item.lastName}
			</div>
			<div className="phone">
				{item.phones.join(', ')}
			</div>
			<div className="birthday">
				{item.birthday}
			</div>
			<div className="kinship">
				Grau de parentesco: {item.kinshipDegree != null ? item.kinshipDegree : "--"}
			</div>
		</li>
	);
}

export default ContactItem;

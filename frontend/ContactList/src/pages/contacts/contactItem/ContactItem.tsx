import React, { useCallback, useState } from 'react';
import type { ContactDto } from 'src/models/ContactDto';
import { ContactService } from '../ContactService';
import './ContactItem.css';

interface ContactItem {
	item: ContactDto;
}

function ContactItem({item}: ContactItem) {
	const [showModal, setShowModal] = useState(false);

	const removeContact = useCallback( async () => {
		await ContactService.deleteContact(item.id!);
	}, []);

	const editContact = useCallback( async () => {
		await ContactService.editContact(item);
	}, []);

	return (
		<li className="ContactItem" key={item.name}>
			<div className="edit-delete-menu">
				<div className="edit noselect" onClick={()=>setShowModal(true)}>&#9998;</div>
				<div className="delete noselect" onClick={removeContact}>&#x2715;</div>
			</div>
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

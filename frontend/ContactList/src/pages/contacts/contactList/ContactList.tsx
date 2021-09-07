import React, { ChangeEvent, useCallback, useState } from 'react';
import ReactModal from 'react-modal';
import type { ContactDto } from 'src/models/ContactDto';
import ContactItem from '../contactItem/ContactItem';
import { ContactService } from '../ContactService';
import './ContactList.css';

interface ContactList {
	items: ContactDto[]
}

const ps = Array<string>();

function ContactList({items}: ContactList) {
	const [showModal, setShowModal] = useState(false);
	const [phoneAmount, setPhoneAmount] = useState(2);
	const [contactName, setContactName] = useState("");
	const [contactLName, setContactLName] = useState("");
	const [birthday, setBirthday] = useState<any>();
	const [kinship, setKinship] = useState("");
	const [phones, setPhones] = useState(ps);

	const resetState = () => {
		setShowModal(false);
		setPhoneAmount(2);
	}

	const openAdd = () => {
		setShowModal(true);
	}

	const addContact = useCallback(async () =>{
		const newContact = {
			name: contactName,
			lastName: contactLName,
			birthday: birthday,
			kinshipDegree: kinship,
			phones: phones
		} as ContactDto;
		await ContactService.postContact(newContact);
	}, [contactName, contactLName, birthday, kinship, phones]);

	const cancelAdd = () => {
		resetState();
		setShowModal(false);
	}

	const addPhone = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
		e.preventDefault();
		e.stopPropagation();
		setPhoneAmount(phoneAmount + 1);
	}

	const PhonesComponent = (amount:number = 1) => {
		let comps = [];

		for(let i = 0; i < amount; i++) {
			comps.push(
				<div className="phone-item spaced-form-div" key={`phone-item-${i}`}>
					<label key={`phone-label-${i}`}>{`Telefone ${i + 1}`}</label>
					<input key={`phone-number-${i}`} type="tel" id="phone" placeholder="(41) 99999-9999" value={phones[i]} onChange={e=>onChangePhones(e, i)}/>
				</div>
			)
		}

		return <div className="phone-list">{comps}</div>
	}

	const onChangeName = (name: ChangeEvent<HTMLInputElement>) => {
		setContactName(name.target.value);
	};

	const onChangeLName = (name: ChangeEvent<HTMLInputElement>) => {
		setContactLName(name.target.value);
	}

	const onChangeBirthday = (bday: React.FormEvent<HTMLInputElement>) => {
		setBirthday(bday.currentTarget.value);
	}
	
	const onChangeKinship = (kinship: ChangeEvent<HTMLInputElement>) => {
		setKinship(kinship.target.value);
	}

	const onChangePhones = (phone: ChangeEvent<HTMLInputElement>, index: number) => {
		ps[index] = phone.target.value;
		setPhones(ps);
	}

	return (
		<div className="ContactList">
			<ul>
				{items.map(item=> <ContactItem item={item}/>)}
			</ul>
			<button className="add-btn" onClick={openAdd}>Novo Contato</button>
			<ReactModal
				ariaHideApp={false}
				className="contact-modal"
				isOpen={showModal}
				contentLabel="Novo Contato"
				style={{overlay:{backgroundColor:"#4141413b"}}}
			>
				<div className="modal-content">
					<form className="contact-component">
						<div className="name-lastname-form">
							<div style={{display:"flex", flexDirection:"column"}}>
								<label>Nome</label>
								<input placeholder="Nome" id="fname" value={contactName} onChange={onChangeName}/>
							</div>
							<div style={{display:"flex", flexDirection:"column"}}>
								<label>Ultimo Nome</label>
								<input placeholder="Ultimo Nome" id="lname" value={contactLName} onChange={onChangeLName}/>
							</div>
						</div>
						<div className="birthday-form spaced-form-div">
							<label>Data de nascimento</label>
							<input type="date" className="birthday" id="birthday" placeholder="01/01/1990" value={birthday} onChange={onChangeBirthday}/>
						</div>
						<div className="kinship-form spaced-form-div">
							<label>Grau de parentesco</label>
							<input className="kinship" placeholder="Nenhum"  value={kinship} onChange={onChangeKinship}/>
						</div>
						{PhonesComponent(phoneAmount)}
						<button onClick={e => addPhone(e)}>Add Phone</button>
					</form>
				</div>
				<div className="modal-buttons">
					<button onClick={addContact}>Salvar</button>
					<button onClick={cancelAdd}>Cancelar</button>
				</div>
			</ReactModal>
		</div>
	);
}

export default ContactList;

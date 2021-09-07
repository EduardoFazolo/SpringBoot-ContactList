import React, { ChangeEvent, useCallback, useEffect, useState } from 'react';
import ReactModal from 'react-modal';
import type { ContactDto } from 'src/models/ContactDto';
import { ContactService } from '../../pages/contacts/ContactService';

interface Props {
	item: ContactDto;
	showModal: boolean;
	onClose(): void;
}

function ContactModal({item, showModal, onClose}: Props) {
	const [phoneAmount, setPhoneAmount] = useState(item.phones.length);
	const [contactName, setContactName] = useState(item.name);
	const [contactLName, setContactLName] = useState(item.lastName);
	const [birthday, setBirthday] = useState<any>(item.birthday);
	const [kinship, setKinship] = useState(item.kinshipDegree);
	const [phones, setPhones] = useState(item.phones);


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

	const PhonesComponent = useCallback((amount:number = 1) => {
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
	}, [phones]);

	const onChangePhones = (phone: ChangeEvent<HTMLInputElement>, index: number) => {
		let newPhones = [...phones];
		newPhones[index] = phone.target.value;
		setPhones([...newPhones]);
	}

	const addPhone = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
		e.preventDefault();
		e.stopPropagation();
		setPhoneAmount(phoneAmount + 1);
	}

	const editContact = useCallback( async () => {
		console.log(phones);
		await ContactService.editContact({
			name: contactName,
			lastName: contactLName,
			birthday: birthday,
			kinshipDegree: kinship,
			phones: phones
		} as ContactDto);
	}, []);

	const saveContact = useCallback(() => {
		editContact();
	},[]);

	return (
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
				<button onClick={saveContact}>Salvar</button>
				<button onClick={onClose}>Cancelar</button>
			</div>
		</ReactModal>
	);
}

export default ContactModal;

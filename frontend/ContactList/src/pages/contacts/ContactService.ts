
import { NetworkService } from "../../commons/NetworkService";
import type { ContactDto } from "src/models/ContactDto";
import type { SearchPageableFilter } from "src/models/PageableFilters";

export class ContactService {
	static async getContacts(filterOptions: SearchPageableFilter): Promise<ContactDto[]> {
		return NetworkService.request<ContactDto[]>({
			url:"contacts",
			method:"GET",
			params: {filterOptions}
		});
	}

	static async postContact(dto: ContactDto): Promise<void> {
		NetworkService.request<void>({
			url:"contacts",
			method:"POST",
			data: dto
		});
	}

	static async deleteContact(id: number): Promise<void> {
		NetworkService.request<void>({
			url:"contacts",
			method:"DELETE",
			params: {id}
		});
	}

	static async editContact(contactDto: ContactDto): Promise<void> {
		NetworkService.request<void>({
			url:"contacts",
			method:"PUT",
			data: contactDto
		});
	}
}
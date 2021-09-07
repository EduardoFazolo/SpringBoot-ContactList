export interface ContactDto {
	id?: number;
	name: string;
	lastName: string;
	phones: string[];
	birthday: string;
	kinshipDegree?: string;
}
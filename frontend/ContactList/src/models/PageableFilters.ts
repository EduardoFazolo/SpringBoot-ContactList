export class PageableFilter {
	pageIndex!: number;
  	pageSize!: number;
}

export class SearchPageableFilter extends PageableFilter {
	searchString!: string;
}
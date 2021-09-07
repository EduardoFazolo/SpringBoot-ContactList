import qs from "qs";
import Axios, { AxiosResponse } from "axios";
//import { sessionStore } from "../../store/sessionStore";
//import { LoginService } from "../api/LoginService";

//eslint-disable-next-line
interface RequestParams<T> {
	url: string;
	method: "POST" | "GET" | "DELETE" | "PUT";
	data?: unknown;
	params?: unknown;
	headers?: any;
	responseType?: any;
	ignoreUnauthorizedError?: boolean;
	onUploadProgress?: (event:any) => void;
}

const apiUrl = "http://localhost:8080/api/v1"

class _NetworkService {
	private _isOffline: boolean = false;

	public async request<T = undefined>({ url, method, data, params, headers, responseType, ignoreUnauthorizedError = false }: RequestParams<T>): Promise<T> {
		const requestHeaders = {
			"Content-Type": "application/json",
			"Accept": "application/json",
			//Authorization: sessionStore.token,
			...headers,
		};


		try {
			const response: AxiosResponse<T> = await Axios({
				url: `${apiUrl}/${url}`,
				method,
				data,
				params,
				responseType,
				headers: requestHeaders,
				paramsSerializer: params => {
					return qs.stringify(params, { arrayFormat: "repeat" });
				}
			});

			this._isOffline = false;

			return response.data;
		} catch (reason) {
			console.warn(reason);


			throw reason;
		}
	}
}

export const NetworkService = new _NetworkService();

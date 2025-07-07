import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { url } from '../models/projecturl';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  registerUser(user:User)
  {
    return this.httpClient.post(`${url}/api/user/`,user);
  }
  loginUser(user:User)
  {
    return this.httpClient.post(`${url}/api/user/login`,user);
  }

}

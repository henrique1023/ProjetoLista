import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  // Metodo de envio de login para o servidor e validação
  login(username: string, password: string) {
    return this.httpClient.post<LoginResponse>('http://localhost:8080/auth/signin', { username, password }).pipe(
      tap((value) => {
        sessionStorage.setItem('token', value.token);
        sessionStorage.setItem('refreshToken', value.refleshToken);
      }
    )); 
  }
}

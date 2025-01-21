import type { AppUser } from './User'

export class LoginResponse {
  token: string
  user: AppUser

  constructor(token: string, user: AppUser) {
    this.token = token
    this.user = user
  }
}

export interface UserLogin {
  email: string
  password: string
}

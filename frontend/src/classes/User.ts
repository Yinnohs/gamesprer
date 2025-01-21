export class AppUser {
  id: string
  name: string
  surname: string
  email: string
  address: string
  phoneNumber: string

  constructor(
    id: string,
    name: string,
    surname: string,
    email: string,
    address: string,
    phoneNumber: string,
  ) {
    this.id = id
    this.name = name
    this.surname = surname
    this.email = email
    this.address = address
    this.phoneNumber = phoneNumber
  }
}

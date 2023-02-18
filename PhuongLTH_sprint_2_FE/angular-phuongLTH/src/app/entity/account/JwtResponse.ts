export class JwtResponse {
  token: string;
  id: number;
  name: string;
  avatar: string;
  roles: any[];

  constructor(token: string, id: number, name: string, avatar: string, roles: any[]) {
    this.token = token;
    this.id = id;
    this.name = name;
    this.avatar = avatar;
    this.roles = roles;
  }
}

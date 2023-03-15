export class JwtResponse {
  token: string;
  id: number;
  name: string;
  avatar: string;
  anony: string;
  roles: any[];
  body: any;

  constructor(token: string, id: number, name: string, avatar: string, anony: string, roles: any[], body: any) {
    this.token = token;
    this.id = id;
    this.name = name;
    this.avatar = avatar;
    this.anony = anony;
    this.roles = roles;
    this.body = body;
  }
}

export class TokenDto {
  value: string;
  body: string | undefined;

  constructor(value: string) {
    this.value = value;
  }
}

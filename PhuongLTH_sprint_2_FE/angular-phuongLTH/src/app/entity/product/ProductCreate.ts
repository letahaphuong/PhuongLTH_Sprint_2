export class ProductCreate {
  idProduct: number;
  autoWhiteBalanceFunction: string;
  createDateProduct: string;
  nameProduct: string;
  categoryProduct: string;
  imageSensor: string;
  infraredVision: string;
  speedRecord: string;
  description: string;
  material: string;
  memory: string;
  resolution: string;
  url?: string;
  price: number;

  constructor(idProduct: number, autoWhiteBalanceFunction: string, createDateProduct: string, nameProduct: string, categoryProduct: string, imageSensor: string, infraredVision: string, speedRecord: string, description: string, material: string, memory: string, resolution: string, url: string, price: number) {
    this.idProduct = idProduct;
    this.autoWhiteBalanceFunction = autoWhiteBalanceFunction;
    this.createDateProduct = createDateProduct;
    this.nameProduct = nameProduct;
    this.categoryProduct = categoryProduct;
    this.imageSensor = imageSensor;
    this.infraredVision = infraredVision;
    this.speedRecord = speedRecord;
    this.description = description;
    this.material = material;
    this.memory = memory;
    this.resolution = resolution;
    this.url = url;
    this.price = price;
  }
}

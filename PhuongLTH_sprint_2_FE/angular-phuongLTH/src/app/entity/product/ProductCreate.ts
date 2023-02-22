import {Category} from "../../dto/product/category";

export class ProductCreate {
  idProduct: number;
  autoWhiteBalanceFunction: string;
  nameProduct: string;
  categoryProduct: Category;
  imageSensor: string;
  infraredVision: string;
  speedRecord: string;
  description: string;
  quantity: number;
  material: string;
  memory: string;
  resolution: string;
  url?: string;
  price: number;


  constructor(idProduct: number, autoWhiteBalanceFunction: string, nameProduct: string, categoryProduct: Category, imageSensor: string, infraredVision: string, speedRecord: string, description: string, quantity: number, material: string, memory: string, resolution: string, url: string, price: number) {
    this.idProduct = idProduct;
    this.autoWhiteBalanceFunction = autoWhiteBalanceFunction;
    this.nameProduct = nameProduct;
    this.categoryProduct = categoryProduct;
    this.imageSensor = imageSensor;
    this.infraredVision = infraredVision;
    this.speedRecord = speedRecord;
    this.description = description;
    this.quantity = quantity;
    this.material = material;
    this.memory = memory;
    this.resolution = resolution;
    this.url = url;
    this.price = price;
  }
}

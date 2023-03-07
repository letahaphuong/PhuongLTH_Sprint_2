import {Customer} from '../../entity/customer/customer';

export interface Orders {
  idOrder: number;
  codeOrder: string; // mã đơn hàng
  paymentStatus: boolean; // tình trạng thanh toán
  address: string; // địa chỉ giao hàng
  phone: string; // số điện thoại giao hàng
  customer: Customer;
  name: string;
  email: string;
}

import {Customer} from '../../entity/customer/customer';
import {CartForm} from './cart-form';

export interface Orders {
  idOrder: number;
  codeOrder: string; // mã đơn hàng
  paymentStatus: boolean; // tình trạng thanh toán
  shippingAddress: string; // địa chỉ giao hàng
  orderPhoneNumber: string; // số điện thoại giao hàng
  customer: Customer;
  orderDetail: CartForm;
}

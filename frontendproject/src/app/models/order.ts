import { Items } from './items';
import { User } from './user';

export class Order {
  orderId: number;
  //user: User;
  orderItems: { item: Items; quantity: number }[];
  orderDate: string;
  billAmount: number;
  paymentStatus: string;

  constructor(
    orderId: number,
    //user: User,
    orderItems: { item: Items; quantity: number }[],
    orderDate: string,
    billAmount: number,
    paymentStatus: string
  ) {
    this.orderId = orderId;
    //this.user = user;
    this.orderItems = orderItems;
    this.orderDate = orderDate;
    this.billAmount = billAmount;
    this.paymentStatus = paymentStatus;
  }
}

// Tipos de la aplicación
export interface Link {
  path: string;
  text: string;
}

export interface LoginForm {
  username: string;
  password: string;
}

export interface RegisterForm {
  firstName: string;
  lastName: string;
  userName: string;
  email: string;
  password: string;
  repeatPassword: string;
  imageUrl: string;
}

// Tipos del servidor
export interface User {
  id?: number;
  firstName?: string;
  lastName?: string;
  userName?: string;
  email?: string;
  password?: string;
  repeatPassword?: string;
  bornDate?: Date;
  imageUrl: string;
  createdAt?: Date;
  roleId?: number;
  money?: number;
}

export interface Notification {
  notificationId: number;
  categoryId: number;
  subject: string;
  message: string;
  sendOn: Date;
}

export interface Category {
  categoryId: number;
  name: string;
  description: string;
  imageUrl: string;
  auctions?: Auction[];
}

export interface Bid {
  userName: string;
  imageUrl: string;
  amount: number;
}

export interface BidDto {
  auctionId: number;
  userId: number;
  amount: number;
}

export interface Auction {
  auctionId?: number;
  auctionName: string;
  auctionDescription: string;
  categoryId: number;
  userId: number;
  startsAt: Date;
  endsAt: Date;
  initialPrice: number;
  imageUrl: string;
}

export interface Issue {
  issueId?: number;
  subject: string;
  message: string;
  createdAt: Date;
  isSolved: boolean;
  userId: number;
}
export interface Promotion {
  promotionId: number;
  code: string;
  endsAt: Date;
  amount: number;
}

export interface UserPromotion {
  code: string;
  userId: number;
}

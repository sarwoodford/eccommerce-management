-- Insert mock users
INSERT INTO Users (username, password, email, role) VALUES 
('admin1', 'Password', 'admin@example.com', 'admin'),
('buyer1', 'BuyPassword', 'buyer@example.com', 'buyer'),
('seller1', 'SellPassword', 'seller@example.com', 'seller'),

-- Ensure 3 cases:
-- Multiple buyers can be added
-- Password can be non-unique
-- Exact same password are hashed properly (Password of admin should not == saved hash password of buyer2)
('buyer2', 'Password', 'buyer2@example.com', 'buyer');

-- Insert mock products
INSERT INTO Products (name, price, quantity, seller_id) VALUES 
('Product 1', 1.00, 10, 1),
('Product 2', 5.00, 50, 1);
TRUNCATE TABLE coupon_set CASCADE;

INSERT INTO coupon_set (id, created_at, modified_at, name, amount, remain)
VALUES ('test_coupon_set_full', '2025-01-01', '2025-01-01', 'test', 100, 100);

INSERT INTO coupon_set (id, created_at, modified_at, name, amount, remain)
VALUES ('test_coupon_set_one', '2025-01-01', '2025-01-01', 'test', 100, 1);

INSERT INTO coupon_set (id, created_at, modified_at, name, amount, remain)
VALUES ('test_coupon_set_zero', '2025-01-01', '2025-01-01', 'test', 100, 0);
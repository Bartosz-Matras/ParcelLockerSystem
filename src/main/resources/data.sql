-- ROLES
INSERT INTO `parcel_locker_system`.`roles` (`id`, `name`) VALUES (1, 'ADMIN');
INSERT INTO `parcel_locker_system`.`roles` (`id`, `name`) VALUES (2, 'WORKER');
INSERT INTO `parcel_locker_system`.`roles` (`id`, `name`) VALUES (3, 'CUSTOMER');

-- PERMISSIONS
INSERT INTO `parcel_locker_system`.`permissions` (`id`, `name`) VALUES (1, 'worker:read');
INSERT INTO `parcel_locker_system`.`permissions` (`id`, `name`) VALUES (2, 'worker:write');
INSERT INTO `parcel_locker_system`.`permissions` (`id`, `name`) VALUES (3, 'worker:delete');
INSERT INTO `parcel_locker_system`.`permissions` (`id`, `name`) VALUES (4, 'customer:read');
INSERT INTO `parcel_locker_system`.`permissions` (`id`, `name`) VALUES (5, 'customer:write');
INSERT INTO `parcel_locker_system`.`permissions` (`id`, `name`) VALUES (6, 'customer:delete');

-- ADMIN
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (1, 1);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (1, 2);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (1, 3);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (1, 4);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (1, 5);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (1, 6);

-- WORKER
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (2, 1);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (2, 2);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (2, 4);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (2, 5);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (2, 6);

-- CUSTOMER
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (3, 4);
INSERT INTO `parcel_locker_system`.`roles_permissions` (`role_id`, `permissions_id`) VALUES (3, 5);
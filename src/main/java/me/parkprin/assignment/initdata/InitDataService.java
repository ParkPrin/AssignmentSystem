package me.parkprin.assignment.initdata;

import me.parkprin.assignment.orders.OrderEntity;
import me.parkprin.assignment.orders.OrderServiceImpl;
import me.parkprin.assignment.orders.OrderStatus;
import me.parkprin.assignment.products.ProductEntity;
import me.parkprin.assignment.products.ProductServiceImpl;
import me.parkprin.assignment.reviews.ReviewEntity;
import me.parkprin.assignment.reviews.ReviewServiceImpl;
import me.parkprin.assignment.role.RoleEntity;
import me.parkprin.assignment.role.RoleServiceImpl;
import me.parkprin.assignment.role.RoleStatus;
import me.parkprin.assignment.userandrole.UserAndRoleEntity;
import me.parkprin.assignment.userandrole.UserAndRoleServiceImpl;
import me.parkprin.assignment.users.UserEntity;
import me.parkprin.assignment.users.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.json.simple.parser.JSONParser;

@Service
public class InitDataService {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private UserAndRoleServiceImpl userAndRoleService;

    public boolean tableIsNull(){
        return userService.tableLength().longValue() == 0 ? true : false;
    }

    public void settingData() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        ClassPathResource classPathResource = new ClassPathResource("./data.json");
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(classPathResource.getFile()));

        List<UserEntity> userEntityList = new ArrayList<UserEntity>();
        List<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
        List<ReviewEntity> reviewEntityList = new ArrayList<ReviewEntity>();
        List<RoleEntity> roleEntityList = new ArrayList<RoleEntity>();

        JSONArray users = (JSONArray)jsonObject.get("users");
        Iterator<JSONObject> usersIterator = users.iterator();
        while (usersIterator.hasNext()){
            JSONObject user = usersIterator.next();
            UserEntity userEntity = UserEntity.builder().name(String.valueOf(user.get("name")))
                    .email(String.valueOf(user.get("email")))
                    .passwd(String.valueOf(user.get("passwd")))
                    .build();
            UserEntity saveReturnUser = userService.save(userEntity);
            userEntityList.add(saveReturnUser);
        }


        JSONArray products = (JSONArray)jsonObject.get("products");
        Iterator<JSONObject> productsIterator = products.iterator();
        while (productsIterator.hasNext()){
            JSONObject product = productsIterator.next();
            ProductEntity productEntity = ProductEntity.builder()
                    .name(String.valueOf(product.get("name")))
                    .details(String.valueOf(product.get("details")))
                    .build();
            ProductEntity saveReturnProduct = productService.save(productEntity);
            productEntityList.add(saveReturnProduct);
        }

        JSONArray reviews = (JSONArray)jsonObject.get("reviews");
        Iterator<JSONObject> reviewsIterator = reviews.iterator();
        while (reviewsIterator.hasNext()) {
            JSONObject review = reviewsIterator.next();
            ReviewEntity reviewEntity = ReviewEntity.builder()
                    .user(userEntityList.get(0))
                    .product(productEntityList.get(1))
                    .content("I like it!")
                    .build();
            ReviewEntity saveReturnReview = reviewService.save(reviewEntity);
            reviewEntityList.add(saveReturnReview);
        }

        JSONArray orders = (JSONArray)jsonObject.get("orders");
        Iterator<JSONObject> orderIterator = orders.iterator();
        int count = 0;
        while (orderIterator.hasNext()){
            JSONObject order = orderIterator.next();
            UserEntity userEntity = userEntityList.get(0);
            ProductEntity productEntity = null;
            ReviewEntity reviewEntity = null;
            OrderStatus orderStatus = OrderStatus.valueOf(String.valueOf(order.get("state")));
            String requestMsg = String.valueOf(order.get("request_msg"));
            String rejectMsg = String.valueOf(order.get("reject_msg"));;
            LocalDateTime completedAt = stringToLocalDateTime(String.valueOf(order.get("completed_at")));
            LocalDateTime rejectedAt = stringToLocalDateTime(String.valueOf(order.get("rejected_at")));
            switch (count){
                case 0:
                    productEntity = productEntityList.get(0);
                    break;
                case 1:
                    productEntity = productEntityList.get(0);
                    break;
                case 2:
                    productEntity = productEntityList.get(1);
                    break;
                case 3:
                    productEntity = productEntityList.get(1);
                    reviewEntity = reviewEntityList.get(0);
                    break;
                case 4:
                    productEntity = productEntityList.get(2);
                    break;
                case 5:
                    productEntity = productEntityList.get(2);
                    break;
                case 6:
                    productEntity = productEntityList.get(2);
                    break;
                default:
                    break;
            }
            OrderEntity orderEntity = OrderEntity.builder().user(userEntity)
                    .product(productEntity)
                    .review(reviewEntity)
                    .status(orderStatus)
                    .requestMsg(requestMsg)
                    .rejectMsg(rejectMsg)
                    .completedAt(completedAt)
                    .rejectedAt(rejectedAt)
                    .build();
            orderService.save(orderEntity);
            count++;
        }
        JSONArray roles = (JSONArray)jsonObject.get("roles");
        Iterator<JSONObject> roleIterator = roles.iterator();
        while(roleIterator.hasNext()){
            JSONObject role = roleIterator.next();
            roleEntityList.add(roleService.save(RoleEntity.builder().
                    name(String.valueOf(role.get("name")))
                    .status(RoleStatus.valueOf(String.valueOf(role.get("status"))))
                    .build()));
        }
        UserEntity userEntity = userEntityList.get(0);
        RoleEntity roleUser = roleEntityList.get(0);
        RoleEntity roleAdmin = roleEntityList.get(2);
        UserAndRoleEntity userAndUser = UserAndRoleEntity.builder()
                .user(userEntity)
                .role(roleUser)
                .build();
        UserAndRoleEntity userAndAdmin = UserAndRoleEntity.builder()
                .user(userEntity)
                .role(roleAdmin)
                .build();
        userAndRoleService.save(userAndUser);
        userAndRoleService.save(userAndAdmin);


    }

    private LocalDateTime stringToLocalDateTime(String localDateTime){
        if (StringUtils.isNotEmpty(localDateTime) && !localDateTime.equals("null")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(localDateTime, formatter);
        } else return null;

    }
}

# Database-with-JPA
## Technologies : Spring boot,jpa,mysql,hibernate
-------------------------------------------------------Read Carefully-------------------------------
### CascadeType
1. It is used in parent side
2. CascadeType.ALL means if we insert, delete anything in owner entity then it effects in child entity
3. If we use specific type then it effects for that type only

### FaceType
1. Default type is LAZY
2. EAGER means it will be loaded when other dependencies will be loaded 
3. LAZY means it will be loaded when we will use 

### mappedBy
1. MappedBy tells hibernate that the foreign key for the relationship is on the other side.

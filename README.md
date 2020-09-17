# Database-with-JPA
## Technologies : Spring boot,jpa,mysql,hibernate
-------------------------------------------------------Read Carefully------------------------------------------------------------
### CascadeType
> CascadeType.ALL means if we insert, delete anything in owner entity then it occures in child entity
> If we use specific type then it occures for that type only

### FaceType
> Default type is LAZY
> EAGER means it will be loaded when other dependencies will be loaded 
> LAZY means it will be loaded when we will use 

### mappedBy
> MappedBy tells hibernate that the foreign key for the relationship is on the other side.

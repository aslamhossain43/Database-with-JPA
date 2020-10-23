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




### Normalization
NORMALIZATION is a database design technique that reduces data redundancy and eliminates undesirable characteristics like Insertion, Update and Deletion Anomalies. Normalization rules divides larger tables into smaller tables and links them using relationships. The purpose of Normalization in SQL is to eliminate redundant (repetitive) data and ensure data is stored logically.


### Database Normal Forms
Here is a list of Normal Forms

1. 1NF (First Normal Form)
2. 2NF (Second Normal Form)
3. 3NF (Third Normal Form)
4. BCNF (Boyce-Codd Normal Form)
5. 4NF (Fourth Normal Form)
6. 5NF (Fifth Normal Form)
7. 6NF (Sixth Normal Form)


## 1NF (First Normal Form)
1. Each attribute/column name must be unique.
2. Each cell should contain a single value.
3. Each row must be unique.
4. Choose a primary key(if not possible then choose a candidate key)

## Prime attributes 
The attributes which are used to form a candidate key are called prime attributes.
## Non-Prime attributes 
The attributes which are not used to form a candidate key are called non-prime attributes.
## Functional dependency
X->Y here value of Y depends on X. It is called functional dependency.
## Partial dependency
If a,b prime attributes and c non-prime attributes then if c depends either a or b then it is called partial dependency.
## Transitive dependency
If X->Y and Y->Z then we can say X->Z this is called transitive dependency

## 2NF (Second Normal Form)
1. It should be 1NF.
2. It should not have partial dependency.

## 3NF (Third Normal Form)
1. It should be 2NF.
2. It doesn't have transitive dependency.
3. All transitive dependencies is removed to place in another table.





















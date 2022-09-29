## Database-with-JPA
## JPA Inheritence: https://www.javatpoint.com/jpa-inheritance-overview
## JPA All >>> https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html
## Entities
https://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html
-------------------------------------------------------Bidirectional One-To-One Relationship-------------------------------
https://www.javaguides.net/2022/02/spring-data-jpa-one-to-one-bidirectional-mapping.html
### Parent
```
@Entity
public class Order {
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    private Address billingAddress;
}
```
### Child
```
@Entity
public class Address {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
``` 
-------------------------------------------------------Unidirectional One-To-One Relationship-------------------------------
https://www.javaguides.net/2022/02/spring-data-jpa-one-to-one-unidirectional-mapping.html
### Parent
```
@Entity
public class Order {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;
}
```
### Child
```
@Entity
public class Address {
}
``` 
-------------------------------------------------------Bidirectional One-To-Many Relationship-------------------------------
https://www.javaguides.net/2019/08/jpa-hibernate-one-to-many-bidirectional-mapping-example.html
### Parent
```
@Entity
public class Order {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();
}
```
### Child
```
@Entity
public class OrderItem {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
``` 
-------------------------------------------------------Unidirectional One-To-Many Relationship-------------------------------
https://www.javaguides.net/2022/02/spring-data-jpa-one-to-many-unidirectional-mapping.html
### Parent
```
@Entity
public class Order {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();
}
```
### Child
```
@Entity
public class Address {
}
```
-------------------------------------------------------Bidirectional Many-To-Many Relationship-------------------------------
https://www.javaguides.net/2022/03/spring-data-jpa-many-to-many-bidirectional-mapping.html
### Parent
```
@Entity
public class User {
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();
}
```
### Child
```
@Entity
public class Role {
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER,mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
``` 
-------------------------------------------------------Unidirectional Many-To-Many Relationship-------------------------------
https://www.javaguides.net/2022/03/spring-data-jpa-many-to-many-Unidirectional-mapping.html
### Parent
```
@Entity
public class User {
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();
}
```
### Child
```
@Entity
public class Role {
}
``` 
## Entity Inheritance
https://docs.oracle.com/javaee/6/tutorial/doc/bnbqn.html

Entity classes can extend non-entity classes, and non-entity classes can extend entity classes. Entity classes can be both abstract and concrete.

### Abstract Entities
Abstract entities are like concrete entities.Abstract entities can be queried just like concrete entities.If an abstract entity is the target of a query, the query operates on all the concrete subclasses of the abstract entity:
```
@Entity
public abstract class Employee {
    @Id
    protected Integer employeeId;
    ...
}
@Entity
public class FullTimeEmployee extends Employee {
    protected Integer salary;
    ...
}
@Entity
public class PartTimeEmployee extends Employee {
    protected Float hourlyWage;
}

```
### Mapped Superclasses
These superclasses are most often used when you have state and mapping information common to multiple entity classes.
```
@MappedSuperclass
public class Employee {
    @Id
    protected Integer employeeId;
    ...
}
@Entity
public class FullTimeEmployee extends Employee {
    protected Integer salary;
    ...
}
@Entity
public class PartTimeEmployee extends Employee {
    protected Float hourlyWage;
    ...
}

```
### Entity Inheritance Mapping Strategies
The following mapping strategies are used to map the entity data to the underlying database:
```
public enum InheritanceType {
    SINGLE_TABLE,
    JOINED,
    TABLE_PER_CLASS
};

```
### The Single Table per Class Hierarchy Strategy
All classes in the hierarchy are mapped to a single table in the database.This is default.

### The Table per Concrete Class Strategy
Each concrete class is mapped to a separate table in the database.All fields or properties in the class, including inherited fields or properties, are mapped to columns in the class’s table in the database.

### The Joined Subclass Strategy
The root of the class hierarchy is represented by a single table, and each subclass has a separate table that contains only those fields specific to that subclass.

## Managing Entities
https://docs.oracle.com/javaee/6/tutorial/doc/bnbqw.html

Entities are managed by the entity manager which is associated with a persistence context: a set of managed entity instances that exist in a particular data store.
### The EntityManager Interface
The EntityManager API creates and removes persistent entity instances, finds entities by the entity’s primary key, and allows queries to be run on entities.

### Container-Managed Entity Managers
An EntityManager instance’s persistence context is automatically propagated by the container to all application components that use the EntityManager.
To obtain an EntityManager instance, inject the entity manager into the application component:
```
@PersistenceContext
EntityManager em;
```
### Application-Managed Entity Managers
the persistence context is not propagated to application components, and the lifecycle of EntityManager instances is managed by the application.Applications create EntityManager instances in this case by using the createEntityManager method of javax.persistence.EntityManagerFactory.
```
@PersistenceUnit
EntityManagerFactory emf;
Then obtain an EntityManager from the EntityManagerFactory instance:

EntityManager em = emf.createEntityManager();
```
The javax.transaction.UserTransaction interface defines methods to begin, commit, and roll back transactions.
```
@Resource
UserTransaction utx;
```
The following example shows how to manage transactions in an application that uses an application-managed entity manager:
```
@PersistenceContext
EntityManagerFactory emf;
EntityManager em;
@Resource
UserTransaction utx;
...
em = emf.createEntityManager();
try {
  utx.begin();
  em.persist(SomeEntity);
  em.merge(AnotherEntity);
  em.remove(ThirdEntity);
  utx.commit();
} catch (Exception e) {
  utx.rollback();
}
```
### CascadeType.REMOVE vs Orphan Removal

1. Orphan Removal = true: When one entity is removed from parent then only parent-child association is detached 
but not deleted from database because child may have other association in whole database. If child has no other association in whole database then it will be removed from database. 
3. CascadeType.REMOVE: When one entity is removed from parent then child is deleted.

### CascadeType
```
@Entity
public class Post {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String name;
 
    @OneToOne(mappedBy = "post",
        cascade = CascadeType.ALL, orphanRemoval = true)
    private PostDetails details;
}
```
1. Cascading only makes sense only for Parent – Child associations.
2. The Parent entity state transition being cascaded to its Child entities.
3. If we use specific type then it effects for that child only.

CascadeType.PERSIST : cascade type presist means that save() or persist() operations cascade to related entities.
CascadeType.MERGE : cascade type merge means that related entities are merged when the owning entity is merged.
CascadeType.REFRESH : cascade type refresh does the same thing for the refresh() operation.
CascadeType.REMOVE : cascade type remove removes all related entities association with this setting when the owning entity is deleted.
CascadeType.DETACH : cascade type detach detaches all related entities if a “manual detach” occurs.
CascadeType.ALL : cascade type all is shorthand for all of the above cascade operations.

### FaceType
```
@Entity
public class University {

    @Id
    private String id;

    private String name;

    private String address;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Student> students;
}
```
1. Default type is EAGER.
2. EAGER means it will be loaded from database with other fields(id, name, address).
3. LAZY means it will be loaded from database on-demand(when we will call university.getStudents()).
4. Best practice is LAZY because EAGER has performance issue.

### mappedBy
1. It is used in parent side
2. MappedBy maps the parent entity which is the property in side the child.

Example: 
```
@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
private List<Address> addresses;
```
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

### 1NF (First Normal Form)
1. Each attribute/column name must be unique.
2. Each cell should contain a single value.
3. Each row must be unique.
4. Choose a primary key(if not possible then choose a candidate key)

### Prime attributes 
The attributes which are used to form a candidate key are called prime attributes.
### Non-Prime attributes 
The attributes which are not used to form a candidate key are called non-prime attributes.
### Functional dependency
X->Y here value of Y depends on X. It is called functional dependency.
### Partial dependency
If a,b prime attributes and c non-prime attributes then if c depends either a or b then it is called partial dependency.
### Transitive dependency
If X->Y and Y->Z then we can say X->Z this is called transitive dependency

### 2NF (Second Normal Form)
1. It should be 1NF.
2. It should not have partial dependency.

### 3NF (Third Normal Form)
1. It should be 2NF.
2. It doesn't have transitive dependency.
3. All transitive dependencies is removed to place in another table.





















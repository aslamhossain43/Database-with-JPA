## Database-with-JPA - https://vladmihalcea.com
```


-----Hibernate & JPA-----
>N-tier architecture-- 
>>Presentation: 
>>Service:
>>Domain model: here JPA works
>>Persistance: Here hibernate works



>Hibernate architecture:
>>Configuration: It reads config properties from spring properties file to connect hibernate to database.
>>SessionFactory: When starting spring boot then spring container creates SessionFactory bean by reading Configuration. Per application per SessionFactory(singleton).  
>>Session: It provides query according to model mapping. every request has new session.
>>Query(it is called API): It persists object to 1st level cache. If commit then go to database. 
>>1st level cache: For same request(same session) same query is used by 1st level cache to improve the performance. For another request (another session) and another query is created then same 1st level cache is not usable that's a problem.
>>2nd level cache: It is not related to hibernate. It is another implementation. It is a solution of 1st level cache. It is related to SessionFactory so for every diffrent session it is shared. so 1st level cache is shared by diffrent request.
>>Transaction: It is for persistence consistency. If transaction.commit() then save data. If transaction fails then rollback.

>JPA:
>>EntityManagerFactory: In JPA, SessionFactory is called EntityManagerFactory. 
>>EntityManager: In JPA, Session it is called EntityManager.
>>Persistence context: In JPA, 1st level cache is called persistence context.
>>Hibernate dialect: It allows Hibernate to generate SQL optimized for a particular relational database.
>>Entity("person") vs Table("employee"): Entity is used in JPQL and Table is used in native/raw query. 
>>Natural key vs Surrogate key: String is natural key and Long is sarrogate key. best practice is surrogate key.
>>The orphanRemoval attribute is going to instruct the JPA provider to trigger a remove entity state transition when a child entity is no longer referenced by its parent entity

>Id generation strategies:
>>@Identity: It increments automatically primary key( table1 - 1,2,3    table2 - 1,2,3) .It doesn't effect other tables and other column. 
>>@Secuence: It is not supported by every database. It share secuence in every table( table1 - 1,4    table2 - 2,3). It is better from identity for table merging.
>>@Auto: Hibernate decides which strategy is best.
>>@Column: It is used to define some properties.
>>@Temporal: It is used to cut timstamp and other things from date.
>>@Transient: It doesn't create column in database.

>Entity lifecycle: It has 4 states:
>>Transient: Person person = new Person(). When it is created then it is in transient states. It has no primary key.
>>Persisted/Managed: em.persist(person) it is called persisted. It is moved inside the 1st level cache/persitent context. If em.commit() then go to database. to update value must call before commit.
>>Detached: It has a record in database. em.detached(); em.close(); It moves from persistent context/1st level cache to transient state but it has primary key. If we want to persist again then em.merge(); It creates a copy of person and go to persistent context/1st level cache;
>>Removed: In database has a record. em.remove(); It removes data from database.

>Everything in database is always bi-directional. It has no Uni-directional so to handle this need associations.
>ORM Associations: 
>>Uni-directional: 
>>Bi-directional: 

>Single table: Every full hierarchy has single table.
>Joined table: Diffrent class has diffrent table(super class has table also) but same id is shared.
>Table per class (per concrete class):  Diffrent sub class has diffrent table(super class has  no table). It is best to me.


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

### @JoinColumn vs mappedBy
```
@Entity
public class Company {
    @OneToMany(mappedBy = "company", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Branch> branches;
}

@Entity
public class Branch {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private Company company;
}

```
1. @JoinColumn: It indicates that this entity is the owner and the table has a column of foreign key.
2. MappedBy: It links by "mappedBy" to make fully bidirectional. It is in parent side for one-to-many.

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





















# 🎓 Spring Data JPA Learning Project

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.5.3-blue.svg)](https://spring.io/projects/spring-data-jpa)
[![MySQL](https://img.shields.io/badge/MySQL-Compatible-blue.svg)](https://www.mysql.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Compatible-blue.svg)](https://www.postgresql.org/)

A comprehensive Spring Data JPA learning project that demonstrates essential concepts through a **Learning Management System** domain model. This project serves as a practical guide for understanding JPA/Hibernate fundamentals, entity relationships, inheritance strategies, and advanced querying techniques.

## 📚 What I Learned

### 🏗️ **1. Entity Design & Architecture**

#### **Base Entity Pattern**
- ✅ **Auditing Fields**: Implemented common fields (`createdAt`, `lastModifiedAt`, `createdBy`, `lastModifiedBy`) in a `@MappedSuperclass`
- ✅ **Code Reusability**: Used `@SuperBuilder` from Lombok for inheritance-friendly builders
- ✅ **Consistency**: Standardized ID generation strategy across all entities

```java
@MappedSuperclass
@SuperBuilder
public class BaseEntity {
    @Id @GeneratedValue
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    // ... other audit fields
}
```

#### **Entity Relationships Mastery**
- 🔗 **One-to-One**: `Lecture ↔ Resources` (bidirectional with unique constraint)
- 🔗 **One-to-Many**: `Course → Sections`, `Section → Lectures` (parent-child hierarchies)
- 🔗 **Many-to-One**: `Section → Course`, `Lecture → Section` (foreign key relationships)
- 🔗 **Many-to-Many**: `Author ↔ Course` (with custom join table configuration)

### 🧬 **2. Inheritance Strategies**

#### **TABLE_PER_CLASS Strategy**
Implemented inheritance hierarchy for content resources:
```
Resources (Base)
├── Video (+ length field)
├── Text (+ content field)
└── File (+ type field)
```

**Key Learning Points:**
- ✅ Each concrete class gets its own table
- ✅ No discriminator column needed
- ✅ Polymorphic queries using `TYPE()` function
- ✅ Better performance for individual class queries

### 🗃️ **3. Embedded Entities & Composite Keys**

#### **Embedded Objects**
- 📍 **Address**: Reusable `@Embeddable` component
- 🔑 **OrderId**: Composite primary key with `@EmbeddedId`

#### **Benefits Learned:**
- ✅ **Value Objects**: Modeling concepts that don't need separate identity
- ✅ **Composite Keys**: Handling natural composite primary keys
- ✅ **Data Modeling**: Better representation of real-world concepts

### 🔍 **4. Advanced Querying Techniques**

#### **Derived Query Methods**
```java
// Method name-based queries (20+ variations implemented)
List<Author> findAllByFirstNameContainingIgnoreCase(String fname);
List<Section> findByCourseIdOrderBySectionOrder(Integer courseId);
List<File> findByTypeIn(List<String> types);
```

#### **Custom JPQL Queries**
```java
// Performance-optimized queries with JOIN FETCH
@Query("SELECT a FROM Author a LEFT JOIN FETCH a.courses")
List<Author> findAllWithCourses();

// Cross-table navigation
@Query("SELECT f FROM File f WHERE f.lecture.section.course.id = :courseId")
List<File> findByCourseId(@Param("courseId") Integer courseId);
```

#### **Query Optimization**
- ⚡ **JOIN FETCH**: Solved N+1 query problems
- ⚡ **Lazy Loading**: Configured appropriate fetch strategies
- ⚡ **Projection**: Custom queries for specific data needs

### 🗄️ **5. Database Configuration & Profiles**

#### **Multi-Database Support**
- 🐬 **MySQL**: Primary configuration with optimized settings
- 🐘 **PostgreSQL**: Alternative profile for different environments
- ⚙️ **Flexible Configuration**: Profile-based database switching

#### **Hibernate Configuration**
```yaml
jpa:
  hibernate:
    ddl-auto: create-drop  # Learning environment
  show-sql: true          # Query logging for learning
```

### 📊 **6. Repository Pattern Mastery**

Created **7 specialized repositories** with different query strategies:

#### **AuthorRepository** - Advanced Query Methods
- 📝 Derived queries with various conditions
- 🔄 Custom JPQL with relationship navigation
- 🎯 Case-sensitive vs case-insensitive searches

#### **ResourcesRepository** - Inheritance Handling
- 🏗️ Polymorphic queries using `TYPE()` function
- 🔍 Type-specific filtering and searching
- 📈 Performance optimizations for inheritance

#### **OrderRepository** - Composite Key Handling
- 🔑 Queries on embedded ID components
- 📅 Date range and time-based filtering
- 🏠 Nested property queries on embedded objects

### 🧪 **7. Data Generation & Testing**

#### **JavaFaker Integration**
- 🎲 **Realistic Test Data**: Generated 50 authors with fake but realistic data
- 🔄 **Relationship Building**: Created associated courses and established relationships
- 🧪 **Data Verification**: Queried and displayed relationships to verify correctness

### 🏛️ **8. Clean Architecture Principles**

#### **Separation of Concerns**
- 📁 **Models**: Pure domain entities with business logic
- 🗄️ **Repositories**: Data access layer abstraction
- ⚙️ **Configuration**: Environment-specific settings
- 🚀 **Application**: Orchestration and initialization

#### **Best Practices Applied**
- ✅ **Lombok Integration**: Reduced boilerplate code significantly
- ✅ **Builder Pattern**: Type-safe object construction
- ✅ **Immutable Entities**: Proper equals/hashCode handling
- ✅ **Documentation**: Comprehensive commenting and naming

## 🛠️ **Technology Stack**

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Core language with modern features |
| **Spring Boot** | 3.5.3 | Application framework |
| **Spring Data JPA** | 3.5.3 | Data persistence layer |
| **Hibernate** | (via Spring Boot) | ORM implementation |
| **MySQL** | 8.0+ | Primary database |
| **PostgreSQL** | 13+ | Alternative database |
| **Lombok** | Latest | Code generation |
| **JavaFaker** | 1.0.2 | Test data generation |
| **Maven** | 3.8+ | Build tool |

## 📁 **Project Structure**

```
src/main/java/com/mehulsuthar/JPA_Project/
├── models/                          # Domain entities
│   ├── BaseEntity.java             # Common audit fields
│   ├── Author.java                 # Many-to-many with Course
│   ├── Course.java                 # Core learning content
│   ├── Section.java                # Course organization
│   ├── Lecture.java                # Individual lessons
│   ├── Resources.java              # Base for inheritance
│   ├── Video.java                  # Video resources
│   ├── Text.java                   # Text resources
│   ├── File.java                   # File resources
│   └── embedded/                   # Value objects
│       ├── Address.java            # Embeddable address
│       ├── Order.java              # Composite key example
│       └── OrderId.java            # Embedded ID
└── repositories/                    # Data access layer
    ├── AuthorRepository.java       # Advanced query methods
    ├── CourseRepository.java       # Basic CRUD
    ├── SectionRepository.java      # Hierarchical queries
    ├── LectureRepository.java      # Resource navigation
    ├── ResourcesRepository.java    # Inheritance queries
    ├── VideoRepository.java        # Specialized resource
    ├── TextRepository.java         # Content search
    ├── FileRepository.java         # File operations
    └── OrderRepository.java        # Composite key handling
```

## 🚀 **Key Features Implemented**

### **Entity Relationships**
- [x] Bidirectional One-to-One relationships
- [x] Unidirectional and bidirectional One-to-Many
- [x] Many-to-Many with custom join tables
- [x] Proper cascade and fetch configurations

### **Inheritance Strategies**
- [x] TABLE_PER_CLASS inheritance
- [x] Polymorphic queries and operations
- [x] Type-specific repository methods

### **Advanced Querying**
- [x] 50+ derived query methods across all repositories
- [x] Custom JPQL queries with complex joins
- [x] Performance-optimized queries with JOIN FETCH
- [x] Criteria-based dynamic queries

### **Data Modeling**
- [x] Embedded objects and value types
- [x] Composite primary keys
- [x] Audit trails and temporal data
- [x] Constraint definitions and validations

## 💡 **Learning Outcomes**

After completing this project, I have gained practical experience in:

1. **🎯 Entity Design**: Creating well-structured domain models
2. **🔗 Relationship Mapping**: Implementing all types of JPA relationships
3. **⚡ Performance Optimization**: Writing efficient queries and avoiding common pitfalls
4. **🗄️ Database Integration**: Working with multiple database vendors
5. **🧪 Testing Strategies**: Using fake data for comprehensive testing
6. **🏛️ Clean Architecture**: Organizing code following best practices
7. **📊 Data Access Patterns**: Implementing repository pattern effectively
8. **🔍 Query Strategies**: Mastering both derived and custom queries

## 🔧 **Setup & Configuration**

### **Prerequisites**
- Java 21+
- Maven 3.8+
- MySQL 8.0+ OR PostgreSQL 13+

### **Database Setup**

**For MySQL:**
```sql
CREATE DATABASE data_jpa;
CREATE USER 'mehul'@'localhost' IDENTIFIED BY 'Mehul_2003';
GRANT ALL PRIVILEGES ON data_jpa.* TO 'mehul'@'localhost';
```

**For PostgreSQL:**
```sql
CREATE DATABASE data_jpa;
CREATE USER mehul WITH PASSWORD 'Mehul_2003';
GRANT ALL PRIVILEGES ON DATABASE data_jpa TO mehul;
```

### **Running the Application**

```bash
# Clone the repository
git clone https://github.com/MehulSuthar-000/JPA_Project.git

# Navigate to project directory
cd JPA_Project

# Run with MySQL (default)
mvn spring-boot:run

# Run with PostgreSQL
mvn spring-boot:run -Dspring.profiles.active=postgresql
```

## 📈 **Performance Insights**

### **Query Optimization Techniques Applied**
- **JOIN FETCH**: Eliminated N+1 problems in relationship loading
- **Lazy Loading**: Configured appropriate fetch strategies
- **Indexed Queries**: Designed queries that leverage database indexes
- **Batch Operations**: Used batch processing for bulk data operations

### **Memory Management**
- **Entity Lifecycle**: Proper understanding of persistence context
- **Session Management**: Efficient use of Hibernate sessions
- **Cache Strategies**: Implemented appropriate caching where beneficial

## 🎯 **Future Enhancements**

- [ ] **Criteria API**: Implement dynamic query building
- [ ] **Specifications**: Add Spring Data JPA Specifications
- [ ] **Caching**: Integrate Redis for second-level caching
- [ ] **Transactions**: Add complex transaction management
- [ ] **REST API**: Expose repositories through REST endpoints
- [ ] **Testing**: Add comprehensive unit and integration tests
- [ ] **Monitoring**: Add query performance monitoring
- [ ] **Migration**: Implement Flyway for database versioning

## 📝 **Lessons Learned**

### **Best Practices Discovered**
1. **Always use JOIN FETCH** for frequently accessed relationships
2. **Bidirectional relationships** require careful equals/hashCode implementation
3. **Composite keys** need `Serializable` implementation
4. **Inheritance strategies** significantly impact query performance
5. **Profile-based configuration** enables flexible deployment

### **Common Pitfalls Avoided**
1. **N+1 Query Problem**: Solved with JOIN FETCH
2. **LazyInitializationException**: Proper session management
3. **Circular References**: Careful toString/equals implementation
4. **Performance Issues**: Strategic use of lazy vs eager loading

---

## 👨‍💻 **Author**
**Mehul Suthar** - *Spring Data JPA Learning Journey*

## 📄 **License**
This project is for educational purposes and is available under the MIT License.

---

*This project represents my comprehensive learning journey with Spring Data JPA, demonstrating real-world application of enterprise Java persistence concepts.*

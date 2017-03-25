// default package
// Generated Mar 25, 2017 6:47:10 PM by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Conference generated by hbm2java
 */
@Entity
@Table(name = "conference", catalog = "dblp", uniqueConstraints = @UniqueConstraint(columnNames = { "Name", "year" }) )
public class Conference implements java.io.Serializable {

	private Integer id;
	private String name;
	private String title;
	private String isbn;
	private int year;

	public Conference() {
	}

	public Conference(String name, int year) {
		this.name = name;
		this.year = year;
	}

	public Conference(String name, String title, String isbn, int year) {
		this.name = name;
		this.title = title;
		this.isbn = isbn;
		this.year = year;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "isbn", length = 200)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "year", nullable = false)
	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
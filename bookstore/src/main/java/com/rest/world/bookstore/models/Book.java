package com.rest.world.bookstore.models;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Book {

    private String oid;
    private String name;
    private String author;
    private Category category;


    public String getOid() {
        return oid;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = Category.valueOf(category.toUpperCase());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return new EqualsBuilder()
                .append(getOid(), book.getOid())
                .append(getName(), book.getName())
                .append(getAuthor(), book.getAuthor())
                .append(getCategory(), book.getCategory())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getOid())
                .append(getName())
                .append(getAuthor())
                .append(getCategory())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("oid", oid)
                .append("name", name)
                .append("author", author)
                .append("category", category)
                .toString();
    }
}

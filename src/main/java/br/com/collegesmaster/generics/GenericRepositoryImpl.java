package br.com.collegesmaster.generics;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.EntityPath;

public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements GenericRepository<T, ID> {

	private EntityManager entityManager;

	public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Transactional
	public List<T> findByAttributeContainsText(String attributeName, String text) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cQuery = builder.createQuery(getDomainClass());
		Root<T> root = cQuery.from(getDomainClass());
		cQuery
			.select(root)
			.where(builder
					.like(root.<String>get(attributeName), "%" + text + "%"));
		TypedQuery<T> query = entityManager.createQuery(cQuery);
		return query.getResultList();
	}

	@Override
	public void customize(QuerydslBindings bindings, EntityPath<?> root) {
		this.customize(bindings, root);
	}

}

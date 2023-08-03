package br.com.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mvc.mudi.model.Pedido;
import br.com.mvc.mudi.model.StatusPedidos;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	@Cacheable("Pedidos")
	List<Pedido> findByStatus (StatusPedidos status, Pageable pageable);
	
	@Query("select p from Pedido p, User u where p.user = u.username and u.username = :usuario")
	List<Pedido> findByUsuario(@Param("usuario")String usuario);

	@Query("select p from Pedido p, User u where p.user = u.username and u.username = :usuario and p.status = :status")
	List<Pedido> findByStatusEUsuario(@Param("status")StatusPedidos status, @Param("usuario")String usuario);
	
}

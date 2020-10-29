package br.com.cairo.estacionamento.core.repository;

import br.com.cairo.estacionamento.core.models.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Vaga v " +
            "  SET v.status =:status " +
            "WHERE v.id =:id ")
    void atualizarStatus(Integer id,
                         Boolean status);

    @Modifying
    @Query(value = " DELETE FROM TOPIC.VAGA v" +
            " WHERE v.ID NOT IN (SELECT ev.VAGA_ID " +
            "                      FROM TOPIC.ESTACIONAMENTO_VAGA ev " +
            "                     WHERE ev.VAGA_ID = v.ID)", nativeQuery = true)
    void deletarVagasNaoUsadas();

}

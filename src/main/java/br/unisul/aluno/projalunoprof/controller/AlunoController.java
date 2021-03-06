package br.unisul.aluno.projalunoprof.controller;


import br.unisul.aluno.projalunoprof.dto.AlunoDTO;
import br.unisul.aluno.projalunoprof.dto.DadosBasicoAlunoDTO;
import br.unisul.aluno.projalunoprof.model.Aluno;
import br.unisul.aluno.projalunoprof.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/{id}")
    public AlunoDTO localizarAluno(@PathVariable(name = "id") Long id) {
        Aluno aluno = alunoRepository.getById(id);
        AlunoDTO dto = new AlunoDTO(aluno);
        return dto;
    }



    @GetMapping
    public List<AlunoDTO> listarAluno() {
        List<AlunoDTO> alunoDTOS = new ArrayList<>();
        List<Aluno>  alunoList = alunoRepository.findAll();
        for (Aluno aluno: alunoList){
            AlunoDTO dto = new AlunoDTO(aluno);
            alunoDTOS.add(dto);
        }
        return alunoDTOS;
    }
    @PostMapping("/nome")
    public List<DadosBasicoAlunoDTO> localizarAlunosPorNomes(@RequestBody List<DadosBasicoAlunoDTO> dtos){
        List<DadosBasicoAlunoDTO> retornoDTO = new ArrayList<>();
        for(DadosBasicoAlunoDTO dto: dtos){
            DadosBasicoAlunoDTO dadosBasicoAlunoDTO =
                    new DadosBasicoAlunoDTO(alunoRepository.findFirstByNome(dto.getNome()));
            retornoDTO.add(dadosBasicoAlunoDTO);

        }
        return retornoDTO;
    }
    @PostMapping("/ids")
    public List<DadosBasicoAlunoDTO> localizarAlunosPorIds(@RequestBody List<DadosBasicoAlunoDTO> dtos){
        List<DadosBasicoAlunoDTO> retornoDTO = new ArrayList<>();
        for(DadosBasicoAlunoDTO dto: dtos){
            DadosBasicoAlunoDTO dadosBasicoAlunoDTO =
                    new DadosBasicoAlunoDTO(alunoRepository.getById(dto.getId()));
            retornoDTO.add(dadosBasicoAlunoDTO);

        }
        return retornoDTO;
    }

    @PostMapping
    public void gravarAluno(@RequestBody AlunoDTO dto) {
        Aluno aluno = dto.converterParaAluno();
        alunoRepository.save(aluno);

    }
}

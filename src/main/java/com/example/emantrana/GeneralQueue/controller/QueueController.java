package com.example.emantrana.GeneralQueue.controller;

import com.example.emantrana.GeneralQueue.dto.DeleteQueueDTO;
import com.example.emantrana.GeneralQueue.dto.QueueDTO;
import com.example.emantrana.GeneralQueue.service.GeneralQueueService;
import com.example.emantrana.models.General_Queue;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@CrossOrigin
public class QueueController {
    @Autowired
    private GeneralQueueService gqs;
    @PostMapping("/add_to_queue")
    public boolean add(@RequestBody QueueDTO q){
        return gqs.add(q);
    }

    @DeleteMapping("/delete_from_queue/{id}")
    public void delete(@PathVariable("id") Long id){
        gqs.delete(id);
    }

    @DeleteMapping("/delete_doctor/{id}")
    public void deleteDoctor(@PathVariable("id") Long id){
        DeleteQueueDTO deleteQueueDTO=new DeleteQueueDTO(id);
        gqs.deleteDoctor(deleteQueueDTO.getId());
    }

    @GetMapping("/get_queue/{id}")
    public List<QueueDTO> get(@PathVariable("id") Long id){
        return gqs.get(id);
    }
}

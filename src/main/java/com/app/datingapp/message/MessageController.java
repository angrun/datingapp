package com.app.datingapp.message;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@Transactional
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageService messageService;


    @ApiOperation("Gets the user with the specific id")
    @PostMapping()
    @Transactional
    public void sendMessage(@RequestBody MessageDto messageView) {
        messageService.sendMessage(messageView);
    }

    @GetMapping("/all/{friendId}")
    public List<Message> getMessages(@RequestHeader(value="Authorization") String authorization, @PathVariable Integer friendId) {
        return messageService.getMessages(authorization, friendId);
    }

    @GetMapping("/{friendId}")
    public void updateMessagesToBeSeen(@RequestHeader(value="Authorization") String authorization, @PathVariable Integer friendId) {
        messageService.updateMessagesToBeSeen(authorization, friendId);
    }

}

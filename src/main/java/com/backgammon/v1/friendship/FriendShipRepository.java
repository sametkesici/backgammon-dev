package com.backgammon.v1.friendship;

import com.backgammon.v1.friendship.model.FriendShip;
import com.backgammon.v1.friendship.model.FriendShipStatus;
import com.backgammon.v1.user.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendShipRepository extends JpaRepository<FriendShip,Long> {

  Optional<FriendShip> findByUserAndFriend(User user , User friend);

  Optional<FriendShip> findByUserAndFriendAndStatus(User user , User friend , FriendShipStatus friendShipStatus);

}

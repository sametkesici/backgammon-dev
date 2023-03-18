package com.backgammon.v1.friendship.model;

import com.backgammon.v1.base.BaseEntity;
import com.backgammon.v1.user.model.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "friendships")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendShip extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "users",
      referencedColumnName = "id"
  )
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "friend",
      referencedColumnName = "id"
  )
  private User friend;

  @Enumerated(EnumType.STRING)
  private FriendShipStatus status;


}

package kr.tatine.manibogo_oms_v2.member.command.application;

public record EditMemberCommand(
        String username,
        String password,
        String repeatPassword
) { }

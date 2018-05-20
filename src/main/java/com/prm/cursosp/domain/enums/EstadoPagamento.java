package com.prm.cursosp.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String estado;

	private EstadoPagamento(Integer codigo, String estado) {
		this.codigo = codigo;
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getEstado() {
		return estado;
	}

	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (EstadoPagamento estado : EstadoPagamento.values()) {
			if (codigo.equals(estado.getCodigo())) {
				return estado;
			}
		}
		throw new IllegalArgumentException("Código inválido");
	}

}

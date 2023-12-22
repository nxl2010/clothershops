<template>
    <div class=" flex flex-row justify-between items-center px-6 py-2">
        <!-- Ícono de Menú (cyan) -->
        <div class="relative text-gray-600 focus-within:text-gray-400">
            <span class="absolute inset-y-0 left-0 flex items-center pl-2">
                <i class="bi bi-search"></i>
            </span>
            <input type="text"
                class="py-2 pl-10 pr-4 rounded-full bg-transparent focus:outline-none border border-transparent focus:border-gray-400 placeholder-gray-400"
                placeholder="Search..." />
        </div>
        <!-- Logo (centrado) -->

        <!-- Ícono de Notificación y Perfil -->
        <div class="space-x-4">
            <button>
                <i class="fas fa-bell text-cyan-500 text-lg"></i>
            </button>
            <!-- Botón de Perfil -->
            <button>
                <i class="fas fa-user text-cyan-500 text-lg"></i>
            </button>
        </div>
    </div>
</template>
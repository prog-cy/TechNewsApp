<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the web site, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * Database settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** Database settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'newsappDB' );

/** Database username */
define( 'DB_USER', 'root' );

/** Database password */
define( 'DB_PASSWORD', '' );

/** Database hostname */
define( 'DB_HOST', 'localhost' );

/** Database charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The database collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         '-j]t8vo%&L4v_?zngyQ4&v^,qL4/CxqW~d /bG{#V2w5OIq$&Z/yI~D{WMMvb!mc' );
define( 'SECURE_AUTH_KEY',  '1|&HV&$9l0&yt:[QW[Mc;1u8:(Z]3N4`p0])&f&/0T=$hZ{y<1g-nfnpoA?pKf$g' );
define( 'LOGGED_IN_KEY',    ']I&K @w4f([Rz- J,f/fo2Q/bjt`1}dzJ4ex54kjB#Wh%4Dong53gZUk.#Tr?_Z?' );
define( 'NONCE_KEY',        'nS:{j5d#Hc8+%iQSoQ- DkrI7;T4-K|34@7g5.cS#lezz)kYedc_FJ]#g54)q-b2' );
define( 'AUTH_SALT',        '^dR5{N3>G]=<z@xs&6VCJ7H|W*Dm!dy%K,]=f>5,^`cYN5;N@egd~UaGq#>zE+Lq' );
define( 'SECURE_AUTH_SALT', 'n_phnM<re%BF~>&3tyak$rDr:j{2Z%aeA:`u!AO[{3!EuaY)3uGB<_%f<LRaxwr:' );
define( 'LOGGED_IN_SALT',   'I4NR)F[d0J*k4tS^GdLih>jhsNgwq>A%4ba0@!TyC1k!p}8J!8||_Q3aJ/k^@tI]' );
define( 'NONCE_SALT',       'u^xiL0TCP-r8k0Vz><0h(nSU9=#(Y4CcbbrJ!=w[p$Z*S*QNuF4( /aK>)K,_3 -' );

/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* Add any custom values between this line and the "stop editing" line. */



/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
